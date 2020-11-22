import urllib.request
import json

JSON_URL = 'https://raw.githubusercontent.com/theikkila/postinumerot/master/postcode_map_light.json'


"""Voit toteuttaa esimerkiksi funktion, joka ottaa parametreinaan etsittävän 
postitoimipaikan nimen sekä sanakirjan postinumeroista ja postitoimipaikoista ja 
palauttaa annettua toimipaikkaa vastaavat postinumerot listana: """

# refactored function
def hae_postinumerot_dict():
        with urllib.request.urlopen(JSON_URL) as response:
            return json.loads(response.read())
postinumerot_dict = hae_postinumerot_dict()
toimipaikat_ja_numerot = {}

# refactored function
def etsi_postinumerot(postitoimipaikka, postinumerot_dict):
    loydetyt = []
    for numero, toimipaikka in postinumerot_dict.items():
        if toimipaikka in toimipaikat_ja_numerot:
            toimipaikat_ja_numerot[toimipaikka].append(numero)
        else:
            toimipaikat_ja_numerot[toimipaikka] = [numero]
    if postitoimipaikka != None:        
        postitoimipaikka = postitoimipaikka.strip().upper()
    else:
        postitoimipaikka = ""        
    if postitoimipaikka in toimipaikat_ja_numerot:
        loydetyt = toimipaikat_ja_numerot[postitoimipaikka]
    else:
        loydetyt = None
    return postinumerot_dict, loydetyt, postitoimipaikka

    

def main():

    def hae_postinumerot():
        with urllib.request.urlopen(JSON_URL) as response:
            return json.loads(response.read())

    postinumerot = hae_postinumerot()
    toimipaikat_ja_numerot = {}

    for numero, toimipaikka in postinumerot.items():
        if toimipaikka in toimipaikat_ja_numerot:
            toimipaikat_ja_numerot[toimipaikka].append(numero)
        else:
            toimipaikat_ja_numerot[toimipaikka] = [numero]
    etsittava = input('Kirjoita postitoimipaikka: ').strip().upper()

    if etsittava in toimipaikat_ja_numerot:
        loydetyt = toimipaikat_ja_numerot[etsittava]
        print('Postinumerot: ' + ', '.join(loydetyt))
    else:
        print('Postitoimipaikkaa ei löytynyt :(')



if __name__ == '__main__':
    main()
