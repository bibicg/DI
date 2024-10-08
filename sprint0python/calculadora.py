# 2 formas de importar las funciones de otro archivo:
# import archivo_funciones
# hay que anteponer el nombre del archivo para cada llamada de funciones (ejemplo: archivo_funciones.funcion_suma(1,2))

# from archivos_funciones import *
# importas todo el código del archivo funciones a tu app.py. En este no debes anteponer el nombre del archivo (ejemplo: funcion_suma(1,2))

from operaciones import suma, resta, multiplicacion, division
# import operaciones

#print("File calculadora __name__ is set to: {}" .format(__name__))

#Comprobación de que accede a todas las operaciones:
suma(4,1)
resta(4,1)
multiplicacion(4, 2)
division(4, 0)
division(4, 2)


def main():
    while True:
        try:
            # sin el float no realiza bien las operaciones (ejemplo: 2 + 3 = 23)
            num1 = float(input("Introduce el primer número: "))
            num2 = float(input("Introduce el segundo número: "))

        except ValueError:
            print("Por favor, introduce números válidos.")
            # sin continue, no vuelve a pedir número, sino que sigue hacia abajo pidiendo la operación
            continue

        user_input = input("Elige la operación (suma, resta, multiplicacion, division): ").strip().lower()

        if user_input == "suma":
            suma(num1, num2)
        elif user_input == "resta":
            resta(num1, num2)
        elif user_input == "multiplicacion":
            multiplicacion(num1, num2)
        elif user_input == "division":
            division(num1, num2)
        else:
            print("Esta operación no está contemplada en esta calculadora.")
            # vuelve atrás, pide los números de nuevo
            continue

        seguirCalculando = input("¿Quieres hacer más operaciones? (s/n): ").strip().lower()
        if seguirCalculando != 's':
            break


if __name__ == "__main__":
    main()
