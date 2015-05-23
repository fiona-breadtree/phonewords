# phonewords
Following instructions from [Phoneword WIKI](http://en.wikipedia.org/wiki/Phoneword)
>
Phonewords are mnemonic phrases represented as alphanumeric equivalents of a telephone number.
In many countries, there exist letters corresponding to each digit on a telephone keypad. The 
letters corresponding to a particular telephone number can form a word, a partial word, an 
acronym, abbreviation, or an alphanumeric combination; these are known as phonewords.
>
Phonewords are the most common vanity numbers, although a few all-numeric vanity phone numbers 
are used. Toll-free telephone numbers are often branded using phonewords; some firms use easily 
memorable vanity telephone numbers like 1-800 Contacts, 1-800-Flowers, 1-800-FREE-411 or 
1-800-GOT-JUNK? as brands for flagship products or names for entire companies.
>
Local numbers are also occasionally used, such as +1-514-AUTOBUS or STM-INFO to reach the
Société de transport de Montréal, but are subject to the constraint that the first few digits 
are tied to a geographic location - potentially limiting the available choices based on which 
telephone exchanges serve a local area.


### North American Standard Keypad 
      2 = A B C	 4 = G H I	6 = M N O	 8 = T U V
      3 = D E F	 5 = J K L	7 = P Q R S	 9 = W X Y Z
      
### How to compile and run?
* Make sure the environment settings for JAVA and ANT.
            Java version should be 1.7, and "1.7.0_51" or above is recommended.
            Ant Version should be 1.9, and "1.9.4" is recommended.
* run : ant prod
* run command : cd prod
* you can change log4j.property under config/
* run one of the following command:

    (a) ./appstart.sh  
    
    (b) ./appstart.sh -p ${phoneFilename}
    
            for example:./appstart.sh -p  config/phones
    
    (c) ./appstart.sh -p ${phoneFilename} -d ${dictionary}
    
            for example: ./appstart.sh -p config/phones -d config/testwords
    
    (d) ./appstart.sh -d ${dictionary}
    
            for example ./appstart.sh -d config/testwords

### The processes
1. Load a Dictionary by a file name, if the file name is null or there is and exceptions when loading, use the default Dictionary “config/words”(this file is the same one of “/usr/share/dict/words” in Linux or MACBOOK). If default Dictionary is missing, use the embedded Dictionary. For every line in dictionary file, remove punctuations and white spaces. For those with illegal format, just ignore. 
2. Pre-process the dictionary to a number words map. For example, with a dictionary with three words “BALL, CALL, ME”, the result is { “2255” :[“BALL”, “CALL”], “63”:[“ME”]}
3. When loading a phone number file, for every phone:
  
  (1) Pre-process phone number to be a correct format. 

  (2) find all sub-strings combinations for the given phone number, for example, for “225563”, all of its cub-strings combinations are :
>[[2, 2, 5, 5, 6, 3], [2, 2, 5, 5, 63], [2, 2, 5, 56, 3], [2, 2, 5, 563], [2, 2, 55, 6, 3], [2, 2, 55, 63], [2, 2, 556, 3], [2, 2, 5563], [2, 25, 5, 6, 3], [2, 25, 5, 63], [2, 25, 56, 3], [2, 25, 563], [2, 255, 6, 3], [2, 255, 63], [2, 2556, 3], [22, 5, 5, 6, 3], [22, 5, 5, 63], [22, 5, 56, 3], [22, 5, 563], [22, 55, 6, 3], [22, 55, 63], [22, 556, 3], [22, 5563], [225, 5, 6, 3], [225, 5, 63], [225, 56, 3], [225, 563], [2255, 6, 3], [2255, 63], [22556, 3], [225563]]

  (3) Look up from PhoneDictionary to find all matched words and then remove the results which have two consecutive numbers. 

  (4) Display all found words  with defined format for the phone number.
