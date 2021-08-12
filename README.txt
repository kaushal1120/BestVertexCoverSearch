Iterative Deepening:
1. The main function in java file IterativeDeepening.java in package ai.bestvertexcover.search runs the Iterative Deepening algorithm.
2. The input for iterative deepening is defined in BestVertexCoverSearch/src/input_files/iterativedeepening_input.txt. Replace the contents
of this input file to run iterative deepening for any other input.

Hill Climbing:
1. The main function in java file HillClimbing.java in package ai.bestvertexcover.search runs the Hill Climbing algorithm.
2. The input for hill climbing is defined in BestVertexCoverSearch/src/input_files/hillclimbing_input.txt. Replace the contents
of this input file to run hill climbing for any other input.

To run the above programs:
1. Unzip the BestVertexCoverSearch-master.zip to BestVertexCoverSearch-master folder.
2. cd BestVertexCoverSearch-master
3. Replace the contents of the respective input files in BestVertexCoverSearch-master/src/input_files to change the input on which either of the 
algorithms are run.

Commands to run the above programs on Windows:

dir /s /B *.java > sources.txt (To populate a list of .java files in a single file sources.txt)
javac -d classes @sources.txt (To compile the java files populated in sources.txt)
java -cp classes ai.bestvertexcover.search.HillClimbing (To run HillClimbing)
java -cp classes ai.bestvertexcover.search.IterativeDeepening (To run IterativeDeepening)

Commands to run the above programs on Linux/Mac

find . -name "*.java" > sources.txt (To populate a list of .java files in a single file sources.txt)
mkdir classes
javac -d classes @sources.txt (To compile the java files populated in sources.txt)
java -cp classes ai.bestvertexcover.search.HillClimbing (To run HillClimbing)
java -cp classes ai.bestvertexcover.search.IterativeDeepening (To run IterativeDeepening)