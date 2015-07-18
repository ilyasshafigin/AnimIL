/*
 *
 */
package ru.ildev.anim.core;

/**
 * Интерфейс анимации. Содержит метод шага анимации, который
 * должен запускаться аниматором, в который добавлен объект анимации.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс)
 * @version 0.2.0
 */
@FunctionalInterface
public interface Animation {

    /**
     * Метод шага анимации.
     *
     * @param elapsedTime пройденное время от прошлого запуска в миллисекундах.
     * @return {@code true}, если данная анимация закончилась и ее нужно удалить
     * из списка аниматора.
     */
    boolean step(float elapsedTime);

}
