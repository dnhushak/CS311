The Jim algorithm takes two input arguments, the first being a text representation of the ingredient list.

Place these in the same directory as /src. If the program can't find the file, it will let you know where to put the file

The format for the ingredient list is as follows:

VERTICES: <number of ingredients + 1 (for starting position)>
0,<starting location ID>
1,<Ingredient A's location ID>
2,<Ingredient B's location ID>
.
.
.
.
EDGES: <Total number of dependencies for all the ingredients>
<vertex ID (dependor)>,<vertexID(dependent)>

Note that every ingredient should be dependent on the starting location

The JimAlgorithm will print out the order of ingredients first according to their vertex ID, then according to their location ID

The format for the Ames graph is the same as the one supplied