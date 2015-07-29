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
 * <pre>{@code
 * // ...
 * AnimationCallback callback = new AnimationCallback(listenObj, "onStart:start, onStop:stop, onPause:pause");
 * Animation animation = new BasicAnimation(obj, new FieldProperty(x, 100), callback);
 * // ...
 * }</pre>
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.0.2
 */
public class AnimationCallback implements AnimationListener {

    /**
     * Регистратор.
     */
    private static final Logger LOGGER = Logger.getLogger(AnimationCallback.class.getName());

    /**
     * Объект.
     */
    private Object object;

    private String onBeginName = null;
    private Method onBeginMethod = null;
    private Class<?> onBeginParameterClass = null;

    private String onStartName = null;
    private Method onStartMethod = null;
    private Class<?> onStartParameterClass = null;

    private String onEndName = null;
    private Method onEndMethod = null;
    private Class<?> onEndParameterClass = null;

    private String onCompleteName = null;
    private Method onCompleteMethod = null;
    private Class<?> onCompleteParameterClass = null;

    private String onRestartName = null;
    private Method onRestartMethod = null;
    private Class<?> onRestartParameterClass = null;

    private String onPauseName = null;
    private Method onPauseMethod = null;
    private Class<?> onPauseParameterClass = null;

    private String onResumeName = null;
    private Method onResumeMethod = null;
    private Class<?> onResumeParameterClass = null;

    private String onStopName = null;
    private Method onStopMethod = null;
    private Class<?> onStopParameterClass = null;

    private String onStepName = null;
    private Method onStepMethod = null;
    private Class<?> onStepParameterClass = null;

    /**
     * Конструктор, устанавливающий объект и каллбэки.
     *
     * @param object   объект.
     * @param callback строка каллбэков.
     */
    public AnimationCallback(Object object, String callback) {
        if (object == null) throw new NullPointerException("object == null");
        if (callback == null) throw new NullPointerException("callback == null");

        for (String property : callback.split(",")) {
            String[] p = property.trim().split(":");
            if (p.length == 2) {
                switch (p[0]) {
                    case AnimationConstants.ON_BEGIN:
                        this.onBeginName = p[1];
                        break;
                    case AnimationConstants.ON_START:
                        this.onStartName = p[1];
                        break;
                    case AnimationConstants.ON_END:
                        this.onEndName = p[1];
                        break;
                    case AnimationConstants.ON_COMPLETE:
                        this.onCompleteName = p[1];
                        break;
                    case AnimationConstants.ON_RESTART:
                        this.onRestartName = p[1];
                        break;
                    case AnimationConstants.ON_PAUSE:
                        this.onPauseName = p[1];
                        break;
                    case AnimationConstants.ON_RESUME:
                        this.onResumeName = p[1];
                        break;
                    case AnimationConstants.ON_STOP:
                        this.onStopName = p[1];
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
     * @param object     объект.
     * @param onBegin    название метода начала анимации.
     * @param onStart    название метода начала цикла анимации.
     * @param onEnd      название метода конца цикла анимации.
     * @param onComplete название метода конца анимации.
     * @param onRestart  название метода перезапуска анимации.
     * @param onPause    название метода паузы анимации.
     * @param onResume   название метода продолжения анимации.
     * @param onStop     название метода остановки анимации.
     * @param onStep     название метода шага анимации.
     */
    public AnimationCallback(Object object, String onBegin, String onStart, String onEnd, String onComplete,
                             String onRestart, String onPause, String onResume, String onStop, String onStep) {
        if (object == null) throw new NullPointerException("object == null");

        this.object = object;
        this.onBeginName = onBegin;
        this.onStartName = onStart;
        this.onEndName = onEnd;
        this.onCompleteName = onComplete;
        this.onRestartName = onRestart;
        this.onPauseName = onPause;
        this.onResumeName = onResume;
        this.onStopName = onStop;
        this.onStepName = onStep;

        this.initialize();
    }

    /**
     * Инициализирует каллбэки.
     */
    private void initialize() {
        // onBegin

        Class<?> objectClass = this.object.getClass();
        boolean found = false;

        if (this.onBeginName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onBeginName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onBeginParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onBeginParameterClass = null;
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
                    Class<?>[] args = this.onBeginParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onBeginMethod = objectClass.getDeclaredMethod(this.onBeginName, args);
                    this.onBeginMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onStart

        objectClass = this.object.getClass();
        found = false;

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
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onEnd

        objectClass = this.object.getClass();
        found = false;

        if (this.onEndName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onEndName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onEndParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onEndParameterClass = null;
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
                    Class<?>[] args = this.onEndParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onEndMethod = objectClass.getDeclaredMethod(this.onEndName, args);
                    this.onEndMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onComplete

        objectClass = this.object.getClass();
        found = false;

        if (this.onCompleteName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onCompleteName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onCompleteParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onCompleteParameterClass = null;
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
                    Class<?>[] args = this.onCompleteParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onCompleteMethod = objectClass.getDeclaredMethod(this.onCompleteName, args);
                    this.onCompleteMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
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
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
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
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }

        // onResume

        objectClass = this.object.getClass();
        found = false;

        if (this.onResumeName != null) {
            while (objectClass != null) {
                for (int i = 0; i < objectClass.getDeclaredMethods().length; i++) {
                    if (objectClass.getDeclaredMethods()[i].getName().equals(this.onResumeName)) {
                        if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 1) {
                            if (objectClass.getDeclaredMethods()[i].getParameterTypes()[0] == AnimationEvent.class) {
                                this.onResumeParameterClass = AnimationEvent.class;
                                found = true;
                                break;
                            }
                        } else if (objectClass.getDeclaredMethods()[i].getParameterTypes().length == 0) {
                            this.onResumeParameterClass = null;
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
                    Class<?>[] args = this.onResumeParameterClass == null ? new Class<?>[]{} : new Class<?>[]{AnimationEvent.class};
                    this.onResumeMethod = objectClass.getDeclaredMethod(this.onResumeName, args);
                    this.onResumeMethod.setAccessible(true);
                } catch (NoSuchMethodException | SecurityException exception) {
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
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
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
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
                    LOGGER.throwing(this.getClass().getName(), "initialize", exception);
                }
            }
        }
    }

    @Override
    public void onEvent(AnimationEvent event) {
        Method method = null;
        Object[] args = null;

        switch (event.type) {
            case AnimationEvent.BEGIN:
                method = this.onBeginMethod;
                args = this.onBeginParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.START:
                method = this.onStartMethod;
                args = this.onStartParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.END:
                method = this.onEndMethod;
                args = this.onEndParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.COMPLETE:
                method = this.onCompleteMethod;
                args = this.onCompleteParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.RESTART:
                method = this.onRestartMethod;
                args = this.onRestartParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.PAUSE:
                method = this.onPauseMethod;
                args = this.onPauseParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.RESUME:
                method = this.onResumeMethod;
                args = this.onResumeParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.STOP:
                method = this.onStopMethod;
                args = this.onStopParameterClass == null ? new Object[]{} : new Object[]{event};
                break;
            case AnimationEvent.STEP:
                method = this.onStepMethod;
                args = this.onStepParameterClass == null ? new Object[]{} : new Object[]{event};
        }

        if (method != null) {
            try {
                method.invoke(this.object, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                LOGGER.throwing(this.getClass().getName(), "onStart", exception);
            }
        }
    }

}
