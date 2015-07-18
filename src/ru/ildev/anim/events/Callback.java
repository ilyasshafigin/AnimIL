/*
 *
 */
package ru.ildev.anim.events;

import ru.ildev.anim.core.AnimationConstants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * Класс каллбека анимации. Он отличается от обычного обработчика событий тем,
 * что можно установить любые методы событий по их названиям.
 * Пример использования:
 * <code>
 * // ...
 * <p>
 * Callback callback = new Callback(listenObj, "onStart:start, onStop:stop, onPause:pause");
 * Animation animation = new BasicAnimation(obj, new FieldProperty(x, 100), callback);
 * <p>
 * // ...
 * </code>
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.0.2
 */
public class Callback implements AnimationListener {

    /**
     * Регистратор.
     */
    private static final Logger LOGGER = Logger.getLogger(Callback.class.getName());

    /**
     * Объект.
     */
    private Object object;

    private String onStartName = null;
    private Method onStartMethod = null;
    private Class<?> onStartParameterClass = null;

    private String onStopName = null;
    private Method onStopMethod = null;
    private Class<?> onStopParameterClass = null;

    private String onRestartName = null;
    private Method onRestartMethod = null;
    private Class<?> onRestartParameterClass = null;

    private String onPauseName = null;
    private Method onPauseMethod = null;
    private Class<?> onPauseParameterClass = null;

    private String onLoopName = null;
    private Method onLoopMethod = null;
    private Class<?> onLoopParameterClass = null;

    private String onStepName = null;
    private Method onStepMethod = null;
    private Class<?> onStepParameterClass = null;

    /**
     * Конструктор, устанавливающий объект и каллбэки.
     *
     * @param object   объект.
     * @param callback строка каллбэков.
     */
    public Callback(Object object, String callback) {
        if (object == null) throw new NullPointerException("object == null");
        if (callback == null) throw new NullPointerException("callback == null");

        String[] propertyList = callback.split(",");
        for (int i = 0; i < propertyList.length; i++) {
            String[] p = propertyList[i].trim().split(":");
            if (p.length == 2) {
                switch (p[0]) {
                    case AnimationConstants.ON_START:
                        this.onStartName = p[1];
                        break;
                    case AnimationConstants.ON_STOP:
                        this.onStopName = p[1];
                        break;
                    case AnimationConstants.ON_PAUSE:
                        this.onPauseName = p[1];
                        break;
                    case AnimationConstants.ON_LOOP:
                        this.onLoopName = p[1];
                        break;
                    case AnimationConstants.ON_STEP:
                        this.onStepName = p[1];
                        break;
                }
            }
        }
        this.object = object;
        this.initialize();
    }

    /**
     * Конструктор, устанавливающий объект и каллбэки.
     *
     * @param object    объект.
     * @param onStart   название метода запуска анимации.
     * @param onStop    название метода остановки анимации.
     * @param onRestart название метода перезапуска анимации.
     * @param onPause   название метода паузы анимации.
     * @param onRepeat  название метода повтора анимации.
     * @param onStep    название метода шага анимации.
     */
    public Callback(Object object, String onStart, String onStop, String onRestart,
                    String onPause, String onRepeat, String onStep) {
        if (object == null) throw new NullPointerException("object == null");
        this.object = object;
        this.onStartName = onStart;
        this.onStopName = onStop;
        this.onPauseName = onPause;
        this.onLoopName = onRepeat;
        this.onStepName = onStep;
        this.initialize();
    }

    /**
     * Инициализирует каллбэки.
     */
    private void initialize() {
        // onStart

        Class<?> objectClass = this.object.getClass();
        boolean found = false;

        if (this.onStartName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onStartName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onStartParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onStartParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onStartParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onStartMethod = objectClass.getDeclaredMethod(this.onStartName, args);
                    this.onStartMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onStop

        objectClass = this.object.getClass();
        found = false;

        if (this.onStopName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onStopName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onStopParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onStopParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onStopParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onStopMethod = objectClass.getDeclaredMethod(this.onStopName, args);
                    this.onStopMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onRestart

        objectClass = this.object.getClass();
        found = false;

        if (this.onRestartName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onRestartName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onRestartParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onRestartParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onRestartParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onRestartMethod = objectClass.getDeclaredMethod(this.onRestartName, args);
                    this.onRestartMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onPause

        objectClass = this.object.getClass();
        found = false;

        if (this.onPauseName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onPauseName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onPauseParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onPauseParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onPauseParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onPauseMethod = objectClass.getDeclaredMethod(this.onPauseName, args);
                    this.onPauseMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onRepeat

        objectClass = this.object.getClass();
        found = false;

        if (this.onLoopName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onLoopName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onLoopParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onLoopParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onLoopParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onLoopMethod = objectClass.getDeclaredMethod(this.onLoopName, args);
                    this.onLoopMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onStep

        objectClass = this.object.getClass();
        found = false;

        if (this.onStepName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onStepName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onStepParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onStepParameterClass = null;
                            found = true;
                            break;
                        }
                    }
                }

                if (found) {
                    break;
                } else {
                    objectClass = objectClass.getSuperclass();
                }
            }

            if (found) {
                try {
                    Class<?>[] args = this.onStepParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onStepMethod = objectClass.getDeclaredMethod(this.onStepName, args);
                    this.onStepMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    Callback.LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }
    }

    @Override
    public void onStart(AnimationEvent event) {
        if (this.onStartMethod != null) {
            try {
                Object[] args = this.onStartParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onStartMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onStart", exception);
            }
        }
    }

    @Override
    public void onStop(AnimationEvent event) {
        if (this.onStopMethod != null) {
            try {
                Object[] args = this.onStopParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onStopMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onStop", exception);
            }
        }
    }

    @Override
    public void onRestart(AnimationEvent event) {
        if (this.onRestartMethod != null) {
            try {
                Object[] args = this.onRestartParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onRestartMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onRestart", exception);
            }
        }
    }

    @Override
    public void onPause(AnimationEvent event) {
        if (this.onPauseMethod != null) {
            try {
                Object[] args = this.onPauseParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onPauseMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onPause", exception);
            }
        }
    }

    @Override
    public void onRepeat(AnimationEvent event) {
        if (this.onLoopMethod != null) {
            try {
                Object[] args = this.onLoopParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onLoopMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onLoop", exception);
            }
        }
    }

    @Override
    public void onStep(AnimationEvent event) {
        if (this.onStepMethod != null) {
            try {
                Object[] args = this.onStepParameterClass == null ? new Object[]{} : new Object[]{event};
                this.onStepMethod.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                Callback.LOGGER.throwing(this.getClass().getName(), "onStep", exception);
            }
        }
    }

}
