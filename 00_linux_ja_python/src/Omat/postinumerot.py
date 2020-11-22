import json

postitoimipaikka = input("Kirjoita postitoimipaikka: ")
cap = postitoimipaikka.upper()

jsonlist = [open("postcode_map_light.json", 'r')]

# luetaan json
with open('postcode_map_light.json', 'r') as tiedosto:
    data=tiedosto.read()

# parse file
obj = json.loads(data)

# metodi joka looppaa kaiken datan

def kaikki(): 
    for x, y in obj.items():
        print(x, y) 
            
def etsiHaku(obj, cap):
    for x, y in obj.items():
        if y == cap:
            key = x
            print(key +", ", end =" ")

print("Postinumerot: ", end=" " )
etsiHaku(obj, cap)
#kaikki()