import json
import urllib.request

# download raw json object
url = "https://raw.githubusercontent.com/theikkila/postinumerot/master/postcode_map_light.json"
data = urllib.request.urlopen(url).read().decode()

postinumero= input("Kirjoita postinumero: ")

#jsonlist = [open("postcode_map_light.json", 'r')]

# luetaan json
#with open('postcode_map_light.json', 'r') as tiedosto:
    #data=tiedosto.read()

# parse file
obj = json.loads(data)

# metodi joka looppaa kaiken datan

def kaikki(): 
    for x, y in obj.items():
        print(x, y) 
            
def etsiHaku(obj, postinumero):
    paikka = ""
    for x, y in obj.items():
        #if postinumero in obj:
        if x == postinumero:
            paikka = y
            print("Postinumerot: " + paikka) 
            break
        else:
            paikka = " "

    if(paikka == None):
        print("Paikkaa ei löydy!")  
            
            
        

etsiHaku(obj, postinumero)

#kaikki()