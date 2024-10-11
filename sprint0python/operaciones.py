print("File operaciones __name__ is set to: {}" .format(__name__)) # así conseguimos que se pueda "lanzar"
                                                                    # esta clase aún no siendo el main

# funciones para suma, resta, multiplicación y división (controla división por 0).
def suma(a, b): # Función de suma. Recibe dos parámetros (serán introducidos por teclado más adelante, en calculadora)
    print ("la suma es:",a + b) # Mensaje al usuario con el resultado de la suma
    return a + b # La función devuelve el resultado de la suma de las dos variables que recibe la función por parámetro

def resta(a, b): # Función de resta. Recibe dos parámetros (serán introducidos por teclado más adelante, en calculadora)
    print("la resta es: ", a - b) # Mensaje al usuario con el resultado de la resta
    return a - b # La función devuelve el resultado de la resta de las dos variables que recibe la función por parámetro

def multiplicacion(a, b): # Función de multiplicación. Recibe dos parámetros (serán introducidos por teclado más adelante, en calculadora)
    print("la multiplicación es: ",a * b) # Mensaje al usuario con el resultado de la multiplicación
    return a * b # La función devuelve el resultado de la multiplicación de las dos variables que recibe la función por parámetro

def division(a, b): # Función de división. Recibe dos parámetros (serán introducidos por teclado más adelante, en calculadora)
    if b == 0: # Controla la división por 0, es decir, que no se permite que el segundo parámetro (b) sea 0
        print ("No se puede dividir entre 0")
        return "No se puede dividor por 0"
    print("la división es: ", a / b) # Mensaje al usuario con el resultado de la división
    return a / b # La función devuelve el resultado de la división de las dos variables que recibe la función por parámetro

# Haz commit y push:
# git add operaciones.py
# git commit -m "Añado archivo operaciones.py"
# git push

# COMPROBACIONES DE QUE EL CÓDIGO FUNCIONA:
print("hola")
suma(4,1)
resta(4,1)
multiplicacion(4, 2)
division(4, 0)
division(4, 2)
