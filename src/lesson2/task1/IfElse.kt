@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import com.sun.org.apache.bcel.internal.generic.SWAP
import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {

    if ((age % 100 >= 5) && (age % 100 <= 20)){
        return "$age лет"
    }
    else if (age % 10 == 1){
        return "$age год"
    }
    else if ((age % 10 >= 2) && (age % 10 <= 4)){
        return "$age года"
    }
    else return "$age лет"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {

    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = s1 + s2 + s3
    val ss = s / 2

    if (s1 > ss) {
        return ss / v1
    }
    else if ((s1 + s2) > ss) {
        return t1 + (ss - s1) / v2
    }
    else if (s1 + s2 < ss) {
        return t1 + t2 + (ss - s1 - s2) / v3
    }
    else return s / (v1 + v2 + v3)
}


/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    var win1 = false
    var win2 = false

    if (kingX == rookX1 || kingY == rookY1)
        win1 = true

    if (kingX == rookX2 || kingY == rookY2)
        win2 = true
     return when{
         !win1 && !win2 -> 0
         win1 && win2 ->3
         win1 -> 1
         else ->2
     }
}
//
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {

    var win1 = false
    var win2 = false

    if (kingX == rookX || kingY == rookY)
        win1 = true

    if (bishopX - bishopY == kingX - kingY || bishopX + bishopY == kingX + kingY)
        win2 = true

    return when{
        !win1 && !win2 -> 0
        win1 && win2 ->3
        win1 ->1
        else ->2
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {

    var massiv = arrayOf(a,b,c)

    massiv.sort()
    var a1= massiv[0]
    var b1= massiv[1]
    var c1= massiv[2]



   if (a1 + b1 > c1 && a1 + c1 > b1 && b1 + c1 > a1) {

       if (a1 * a1 + b1 * b1 < c1 * c1) {
           return 2
       }
       else if (a1 * a1 + b1 * b1 > c1 * c1) {
           return 0
       }
       else if (a1 * a1 + b1 * b1 == c1 * c1) {
           return 1
       }
       else return -1
   }
   else return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (a > d || b < c) -> -1
    (a <= d && d <= b && a <= c) -> d - c
    (c <= b && b <= d && a < c) -> b - c
    (c <= a && b <= d) -> b - a
    else -> d - a
}