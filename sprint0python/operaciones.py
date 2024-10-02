print("File operaciones __name__ is set to: {}" .format(__name__))

# funciones para suma, resta, multiplicación y división (controla división por 0).
def suma(a, b):
    print ("la suma es:",a + b)
    return a + b

def resta(a, b):
    print("la resta es: ", a - b)
    return a - b

def multiplicacion(a, b):
    print("la multiplicación es: ",a * b)
    return a * b

def division(a, b):
    if b == 0:
        print ("No se puede dividir entre 0")
        return "No se puede dividor por 0"
    print("la división es: ", a / b)
    return a / b
# Haz commit y push:
# git add operaciones.py
# git commit -m "Añado archivo operaciones.py"
# git push

print("hola")
suma(4,1)
resta(4,1)
multiplicacion(4, 2)
division(4, 0)
division(4, 2)
