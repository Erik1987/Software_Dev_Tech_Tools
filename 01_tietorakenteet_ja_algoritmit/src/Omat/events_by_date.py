import json
import urllib.request


# Huomaathan että käytän /?limit=10 osoitteessa laskentatehon säästämiseksi!!!
url = "http://open-api.myhelsinki.fi/v1/events/?limit=10"
data = urllib.request.urlopen(url).read().decode('utf-8')
obj = json.loads(data)
   
def testitulostus(obj):
    for x, y in obj.items(): 
       print(x, y)
       #return x, y

"""
# source: https://www.tutorialspoint.com/python_data_structure/python_sorting_algorithms.htm

def bubblesort(list):

# Swap the elements to arrange in order
    for iter_num in range(len(list)-1,0,-1):
        for idx in range(iter_num):
            if list[idx]>list[idx+1]:
                temp = list[idx]
                list[idx] = list[idx+1]
                list[idx+1] = temp


list = [19,2,31,45,6,11,121,27]
bubblesort(list)
print(list) """

def getAndListDatesAndTimes(obj):
    datesList = []
    idList = []
    teemaList = []
    for x in obj['data']:
        
        if x['event_dates']['starting_day'] != None: 
            
            datesList.append(x['event_dates']['starting_day']) #[0:10])
            idList.append(x['id'])
            teemaList.append(x['name']['fi'])
            
            
        else:
            print("Missing event date") #[0:10])
        
        #print(x['event_dates']['starting_day'][0:10])
        #monthsList.append(obj['data'][i]['event_dates']['starting_day'][5:7])
        #daysList.append(obj['data'][i]['event_dates']['starting_day'][8:10])
        #timesList.append(obj['data'][i]['event_dates']['starting_day'][11:16])
        #print(datesList)
        #print(idList)
    
    return datesList, idList, teemaList
datesList, idList, teemaList = getAndListDatesAndTimes(obj)

def bubblesortByDates(datesList, idList, teemaList):

# Swap the elements to arrange in order
    for iter_num in range(len(datesList)-1,0,-1):
        for idx in range(iter_num):
            
            if datesList[idx] != None: 
                if datesList[idx]>datesList[idx+1]:
                        # sorting dates
                        temp = datesList[idx]
                        datesList[idx] = datesList[idx+1]
                        datesList[idx+1] = temp
                        # sorting ID
                        temp2 = idList[idx]
                        idList[idx] = idList[idx+1]
                        idList[idx+1] = temp2
                        # sorting Theme
                        temp3 = teemaList[idx]
                        teemaList[idx] = teemaList[idx+1]
                        teemaList[idx+1] = temp3
            else:
                del datesList[iter_num]
    return datesList, idList, teemaList
bubblesortByDates(datesList, idList, teemaList)

def printData(datesList, idList, teemaList):
    
    #MAX = max(datesList)
    #MIN = min(datesList)
    #global t1
    #global t2
    #global t3
    checkDay = []
    checkYear = []
    checkMonth = []
    checkTime = []
    a = 0
    h = 1
    for x in datesList:
            boole = 0
            checkYear.append(int(x[0:4]))
            checkMonth.append(int(x[5:7]))
            checkDay.append(int(x[8:10]))
            checkTime.append(str(x[11:16]))
          
            
    """print(checkYear)
    print(checkMonth)
    print(checkDay)
    print(checkTime)
    print(teemaList)"""
    i = 0
    for p in (datesList):
        print(p[0:10])
        print()
       
        print("     ", p[11:16], teemaList[i])
        i += 1
        print()

    return a
a = printData(datesList, idList, teemaList) 