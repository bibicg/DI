# 2 formas de importar las funciones de otro archivo:
# import archivo_funciones
# hay que anteponer el nombre del archivo para cada llamada de funciones (ejemplo: archivo_funciones.funcion_suma(1,2))

# from archivos_funciones import *
# importas todo el codigo del archivo funciones a tu app.py. En este no debes anteponer el nombre del archivo (ejemplo: funcion_suma(1,2))

from operaciones import suma, resta, multiplicacion, division # importamos el archivo operaciones y sus funciones, ya
                                                                # que son las que va a usar nuestra calculadora


#print("File calculadora __name__ is set to: {}" .format(__name__))

#Comprobación de que accede a todas las operaciones:
#suma(4,1)
#resta(4,1)
#multiplicacion(4, 2)
#division(4, 0)
#division(4, 2)


def main(): # definimoa el main de nuestra calculadora, que es lo que ejecutaremos
    while True: # Metemos la operativa dentro de un bucle, mientras sea cierto
        try:
            # sin el float no realiza bien las operaciones (ejemplo: 2 + 3 = 23)
            num1 = float(input("Introduce el primer número: ")) # mensaje donde se pide al usuario que intruzca un número
                                                                # que será el primer operando que reciban por parámatro lsa funciones
            num2 = float(input("Introduce el segundo número: ")) # Lo mismo para el segundo número

        except ValueError: # metemos en un try-catch la petición de números por teclado y controlamos que sean válidos
            print("Por favor, introduce números válidos.") # Mensaje por pantalla para que el usuario introduzca n.validos
            continue # sin continue, no vuelve a pedir número, sino que sigue hacia abajo pidiendo la operación

        user_input = input("Elige la operación (suma, resta, multiplicacion, division): ").strip().lower()
        # Lo mismo para la operación: la solicitamos por pantalla
        # Los datos introducidos por teclado se meten en las variables declaradas previamente con el input

        if user_input == "suma": # condicionales if-elif para indicar a la función que llamaremos según la operación
                                # que ha elegido el usuario por pantalla
            suma(num1, num2) # Si usuario elige suma, llamamos a la función suma y le pasamos los números que el usuario
                            # ha introducido por pantalla y que hemos metido en las variables num1 y num2
        elif user_input == "resta": # si el usuario elige resta
            resta(num1, num2) # llamamos a la función resta y le pasamos las variables
        elif user_input == "multiplicacion": # si el usuario elige multiplicacion
            multiplicacion(num1, num2) # llamamos a la función multiplicacion y le pasamos las variables
        elif user_input == "division": # si el usuario elige dvision
            division(num1, num2) # llamamos a la función division y le pasamos las variables
        else: # si el usuario escribe algo diferente a lo que hemos contemplado previamente
            print("Esta operación no está contemplada en esta calculadora.")
            continue # vuelve atrás, pide los números de nuevo

        seguirCalculando = input("¿Quieres hacer más operaciones? (s/n): ").strip().lower() # si el usuario quiere seguir operando
        if seguirCalculando != 's': #debe escribir s, sino, se parará el programa
            break


if __name__ == "__main__":
    main()
