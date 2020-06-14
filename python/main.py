import requests
from bs4 import BeautifulSoup
import pymongo

def dodajCeneMiniaturkeOpis(tablica):
  iliosc = len(tablica)
  licznik = 0
  for t in tablica:
    licznik += 1
    print(licznik, iliosc)
    rtv = rtvRequest(t[0])
    t.extend(rtv)
    myquery = { "strona": t[0] }
    newvalues = { "$set": { "opis": t[1], "miniaturka": t[2], "cena": t[3] } }
    mycol.update_one(myquery, newvalues)
    #print(t)

def rtvRequest(link):
  opis = ""
  miniaturka = ""
  cenaOutlet = -1
  page = requests.get(link)
  soup = BeautifulSoup(page.content, 'html.parser')
  przycisk = soup.findAll('button', class_='sales-tabs-link')
  if len(przycisk) < 4:
    return [opis , miniaturka, cenaOutlet]
  try:
    cena = [str(s) for s in przycisk[1].text.split() if s.isdigit()]
    obraz = soup.find(id="big-photo")['href']
    opisZupa = soup.find('div', class_='product-attributes')
    rows = opisZupa.findAll('div', class_='attributes-row')
    opisText = ""
    for r in rows:
      temp = r.text.split()
      outtemp = ""
      for t in temp:
        outtemp += t + " "
      opisText += outtemp + '\n'
    #print(opisText)
    #print(opis.text.replace('\n', ' ').replace('\t', '').replace(u'\xa0', u' ').split(":"))
    po = ""
    for c in cena:
      po += c
    opis = opisText[:-2]
    miniaturka = obraz
    cenaOutlet = int(po)

    #print(link, obraz, int(po))
  except:
    print("Problem z przetwarzaniem: ", link)
  return [opis , miniaturka, cenaOutlet]

print("Start aktualizacji")

myclient = pymongo.MongoClient("mongodb://192.168.1.147:27017/", username='admon', password='arbuz123')
mydb = myclient["mobilki"]
mycol = mydb["oferty"]

wszystkie = mycol.find()
sprawdzanie = []

for x in wszystkie:
  sprawdzanie.append([x['strona']])

dodajCeneMiniaturkeOpis(sprawdzanie)