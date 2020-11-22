from postinumerot import etsi_postinumerot
from postinumerot import hae_postinumerot_dict

    #1. annettua nimeä ei löydy lainkaan aineistosta
    #2. postitoimipaikan nimellä löytyy yksi postinumero
    #3. postitoimipaikan nimellä löytyy useita postinumeroita

def test_nimea_ei_loydy():
    try:
        mock_pstmp = "Wizard"
        postinumerot_dict = hae_postinumerot_dict()
        postinumerot_dict, loydetyt, mock_pstmp = etsi_postinumerot(mock_pstmp, postinumerot_dict)
    except:
        print("Ei loydy postitoimipaikkaa "+mock_pstmp)
    
        assert loydetyt == None

# function to count .values()
def count_values_def():
    postinumerot_dict = hae_postinumerot_dict()
    count = {}
    for i in postinumerot_dict.values(): 
        count[i] = count.get(i, 0) + 1
    return count

def test_pstmp_vain_yksi():
    data = count_values_def()
    new = []
    for x, y in data.items():
        if y == 1:
            new.append(x)
    assert {k: data[k] for k in new if k in data or k in data.keys() ==1}

def test_pstmp_useita():
    data = count_values_def()
    new = []
    for x, y in data.items():
        if y > 1:
            new.append(x)
    assert {k: data[k] for k in new if k in data or k in data.keys() > 1}