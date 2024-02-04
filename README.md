## Plagaism Detector for text and code
The program takes 2 inputs, File1 and File2. It reads the content of both the files and creates two HashMap for each word in the input file. It checks the count of common words between 2 inputs and Each word has an associated weight corresponding to its length that contributes to the common length. It detects plagiarism if the common length is more than 40%.
The program also detects if the input file has source code or not. Since the source code files have many common keywords that may contribute to plagiarism, the program normalizes the input for source code before checking plagiarism.

### The program Displays output as 0 (No Plagiarism) or 1(Plagiarism found)

To run for particular input files : pass the relative/absolute file path to make command as shown below:

``` make FILE1=../data/okay08/1.txt FILE2=../data/okay08/2.txt run ```

To test for multiple files :
Input files should be present in directory structure as
../data/okayXX/1.txt and ../data/okayXX/2.txt
and
../data/plagiarismXX/1.txt and ../data/plagiarismXX/2.txt

``` make test ```

To clean the class files created during run or test make commands use:

``` make clean ```