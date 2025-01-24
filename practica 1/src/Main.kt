//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    triangulos(5, 5, 5)
}

fun triangulos(lado1: Int, lado2: Int, lado3: Int) {
    if (lado1>0 && lado2>0 && lado3>0) {
        if (lado1 == lado2 && lado2 == lado3) {
            println("Es un triángulo equilátero")
        } else if (lado1 == lado2 || lado2 == lado3 || lado3 == lado1) {
            println("Es un triángulo isósceles")
        } else {
            println("Es un triángulo escaleno")
        }
    } else {
        println("Los valores no forman un triángulo válido")
    }
}