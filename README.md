# phonewords
Following instructions from WIKI: http://en.wikipedia.org/wiki/Phoneword

Phonewords are mnemonic phrases represented as alphanumeric equivalents of a telephone number.
In many countries, there exist letters corresponding to each digit on a telephone keypad. The 
letters corresponding to a particular telephone number can form a word, a partial word, an 
acronym, abbreviation, or an alphanumeric combination; these are known as phonewords.

Phonewords are the most common vanity numbers, although a few all-numeric vanity phone numbers 
are used. Toll-free telephone numbers are often branded using phonewords; some firms use easily 
memorable vanity telephone numbers like 1-800 Contacts, 1-800-Flowers, 1-800-FREE-411 or 
1-800-GOT-JUNK? as brands for flagship products or names for entire companies.

Local numbers are also occasionally used, such as +1-514-AUTOBUS or STM-INFO to reach the
Société de transport de Montréal, but are subject to the constraint that the first few digits 
are tied to a geographic location - potentially limiting the available choices based on which 
telephone exchanges serve a local area.

North American Standard Keypad 

2 = A B C	 4 = G H I	6 = M N O	 8 = T U V

3 = D E F	 5 = J K L	7 = P Q R S	 9 = W X Y Z

How to compile and run?

(1) Make sure the environment settings for JAVA and ANT.
 
 Java version should be 1.7, and "1.7.0_51" or above is recommended.
 Ant Version should be 1.9, and "1.9.4" is recommended.
 
(2) run : ant prod

(3) run command : cd prod

(4) you can change log4j.property under config/

(5) run one of the following command:

  (a) ./appstart.sh 
  
  (b) ./appstart.sh -p ${phoneFilename}
  
      for example:./appstart.sh -p  config/phones
      
  (c) ./appstart.sh -p ${phoneFilename} -d ${dictionary}
  
      for example: ./appstart.sh -p config/phones -d config/testwords
      
  (d) ./appstart.sh -d ${dictionary}
  
      for example ./appstart.sh -d config/testwords
