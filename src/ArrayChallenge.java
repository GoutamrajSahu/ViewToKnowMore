/*
Have the function ArrayChallenge(strArr) read the array of strings stored in strArr,
which will contain 2 elements: the first element will be a sequence of characters representing a word,
and the second element will be a long string of comma-separated words, in alphabetical order,
that represents a dictionary of some arbitrary length.
For example: strArr can be: ["worlcde", "apple,bat,cat,goodbye,hello,yellow,why,world"].
Your goal is to determine the minimum number of characters, if any, can be removed from the word so that it matches one of the words from the dictionary.
In this case, your program should return 2 because once you remove the characters "c" and "e" you are left with "world" and that exists within the dictionary.
If the word cannot be found no matter what characters are removed, return -1.

Examples:
Input: ["baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"]
Output: 4
Input: ["apbpleeeef", "a,ab,abc,abcg,b,c,dog,e,efd,zzzz"]
Output: 8
*/


import java.util.*;

public class ArrayChallenge {
    public static void main(String[] args){
        ArrayList<String> copyArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String ArrayString = sc.nextLine();
        boolean finalResult = false;
        ArrayString = ArrayString.substring(2,ArrayString.length()-1);
        StringTokenizer str = new StringTokenizer(ArrayString,"\"");
        while(str.hasMoreTokens()){
            copyArr.add(str.nextToken());
        }
        String arr[] = new String[copyArr.size()];
        copyArr.remove(1);
        for(int i = 0; i<copyArr.size();i++){
            arr[i] = copyArr.get(i);
        }

        ArrayList<String> dir = new ArrayList<>();
        String word = arr[0];

        StringTokenizer str2 = new StringTokenizer(arr[1],",");
        while(str2.hasMoreTokens()){
            dir.add(str2.nextToken());
        }
//            System.out.println("DIR: "+dir); // dir
//            System.out.println("wORD: "+word); // word

        char []charArrayOfWord = new char[word.length()];
        for(int i = 0; i<word.length(); i++){
            charArrayOfWord[i] = word.charAt(i);
        }

        int biggestLengthInDir=0;
        for(int i = 0; i<dir.size(); i++){
            if(biggestLengthInDir < dir.get(i).length()){
                biggestLengthInDir = dir.get(i).length();
            }
        }
//        System.out.println(biggestLengthInDir); //Biggest Length InDir
        int currentBiggestLengthInDir= biggestLengthInDir;

        outer:
        while(currentBiggestLengthInDir>0){
            for(int i = 0 ;i<dir.size(); i++){
                if(dir.get(i).length() == currentBiggestLengthInDir){
//                    System.out.println(dir.get(i)); //Getting every word.
                    int currentDirWordLength = dir.get(i).length();
                    char currentWordChars[] = new char[currentDirWordLength];
                    for(int j = 0; j<currentDirWordLength ; j++){
                        currentWordChars[j] = dir.get(i).charAt(j);
                    }
                    int matchWordCount = 0;
                    int checkIndexComplete = 0;
                    for(int k = 0; k < currentDirWordLength; k++){
                        inner:
                        for(int l = checkIndexComplete; l < word.length(); l++){
                            if(currentWordChars[k] == word.charAt(l)){
                                matchWordCount++;
                                checkIndexComplete = l+1;
                                break inner;
                            }
                        }
                    }
                    if(matchWordCount == currentDirWordLength){
                        System.out.println(word.length()-matchWordCount);
                        finalResult = true;
                        break outer;
                    }
                }
            }
            currentBiggestLengthInDir --;
        }

        if(finalResult == false){
            System.out.println(-1);
        }
    }
}
