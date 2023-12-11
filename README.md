# chat-agents-si

![Static Badge](https://img.shields.io/badge/authors-Mat(2.718)i-93c6d6)
![GitHub License](https://img.shields.io/github/license/matei-george/chat-agents-si)
![GitHub last commit (branch)](https://img.shields.io/github/last-commit/matei-george/chat-agents-si/main)
![Static Badge](https://img.shields.io/badge/language-Java-82aeb1)
![Static Badge](https://img.shields.io/badge/built_with-Java_Swing-ffb997)
![Static Badge](https://img.shields.io/badge/built_with-JADE-ffb997)

----
<p align="center">
Un ecosistem de tip Chat-Agents scris in Java SE 1.8 cu Swing si JADE.
</p>

## Cuprins
1. [Introducere](#introducere)
2. [Tech-Stack](#tech-stack)
3. [Utilizare](#utilizare)
    1. [prima utilizare](#prima-oara-incepeti-aici)
    2. [aplicatia propriu-zisa](#aplicatia-propriu-zisa)
5. [Bibliografie](#bibliografie)
6. [Note](#note)

## Introducere
Proiectul face parte din disciplina Sisteme Inteligente in cadrul FIESC, Calculatoare. Scopul acestuia este de a realiza un ecosistem de agenti cu rol de ChatAgents, ce comunica intre ei prin trimiterea si receptionarea mesajelor.

Agentii se identifica cu ajutorul `DF-Agent` printr-un `serialVersionUID`. Comunicarea dintre ei este realizata cu ajutorul unui sistem descentralizat ( Peer-to-Peer ).

De asemenea, aplicatia furnizeaza un sistem de salavare a conversatiilor intr-un fisier extern de tip `.txt`. 

## Tech Stack

Conceputa, stilizata si scrisa cu :

- *Eclipse IDE* ➡ IDE popular pentru developmentul software-ului in limbajul JAVA.
- *Java SE 1.8* ➡ Limbaj de programare hibrid folosit pentru software scalabil si robust.
- *Java Swing* ➡ Toolkit de creare, modificare si gestionare a GUI. Precursorul *JavaFX*
-  *Java Agent DEvelopment Framework (JADE)* ➡ Framework ce simplifica implementarea sistemelor multi-agent.

## Utilizare

### Prima oara? Incepeti aici:
> Aplicatia este facuta sa ruleze in EclipseIDE cu Java SE 1.8. Aceasta are nevoie de un profil de configuratie pentru a nu avea probleme pe viitor. Asigurati-va ca profilul de configuratie este nou si luat de la 0, fara sa includa argumente in plus. 

Pentru a crea o configuratie noua in eclipse, se executa urmatorii pasi:

 `Run > Run Configurations > Java Application (click dreapta) > New Configuration`

In caseta noii configurari, introducem urmatoarele :

- `Name : Orice nume al configurarii se doreste`
- `Proiect : Browse (click) > proiectChatAgents (click) > Ok`
- `Main class : Search (click) > GUIAgent (in bara de cautare) > Ok`

### O scurta prezentare
GUI-ul este special creat sa fie simplu, usor de utilizat, intuitiv si cu un foarte mic learning-curve.

La prima rulare a programului, interfata este prezentata. Din meniul drop-down putem alege userii de pe care dorim sa conversam. Aplicatia prezinta un fel de *group chat*, astfel incat userii pot vedea mesajele comunicate intre acestia.
<p align="center">
	<img src="https://i.ibb.co/2KNF9tq/Interfata.png" alt="Interfata" 		border="0" width=400;>
</p>

### Aplicatia propriu-zisa
Textul este scris cu ajutorul zonei din stanga jos a aplicatiei. Dupa ce a scris mesajul, userul are doua posiblitati :
-- poate trimite mesajul prin butonul `Trimite`
-- poate salva conversatia intr-un fisier text prin butonul `Salveaza Istoric`

Un mic exemplu de mai jos demonstreaza cum functioneaza aplicatia. Observam ca dispune si de un feature de timestamping, deci mesajele sunt monitorizate dupa ora, minut si secunda.
<p align="center">
<img src="https://i.ibb.co/3TvSZgx/Interfata-2.png" alt="Interfata-2" border="0" width=400;>
</p>

In conditiile in care userul doreste sa salveze istoricul unei conversatii, poate face acest lucru prin apasarea butonului `Salveaza Istoric`. Acesta se va stoca intr-un mesaj de tip `.txt`, sub numele de `message_history.txt`
<p align="center">
<img src="https://i.ibb.co/5WTBMZK/salvat-prompt.png" alt="salvat-prompt" border="0" width=400;>
</p>
<p align="center">
<img src="https://i.ibb.co/Hp77sgZ/mesaj-salvat.png" alt="mesaj-salvat" border="0" width=400;>
</p>

In concluzie, aplicatia respectiva isi indeplineste cu succes scopul, indeplinind cerintele cerute in cadrul laboratorului de Sisteme Inteligente, integrand un ecosistem de agenti care pot comunica intre ei cu ajutorul implementarii Peer-to-Peer.

## Bibliografie
- [Eclipse Docs](https://www.eclipse.org/documentation/)
- [JavaDocs](https://docs.oracle.com/javase/8/docs/api/)
- [Java Swing Docs](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)
- [Java JADE Docs](https://jade.tilab.com/documentation/tutorials-guides/)

## Note
Copyright © 2023 [Mat(2.718)](https://github.com/matei-george) <br>
Toate drepturile rezervate, sub licenta [MIT](https://mit-license.org/)
