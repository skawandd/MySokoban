# MySokoban

## How To use

### Play a Game

Chose a non-*ia_level* file and play with the keyboard arrows.

### Solve a Grid with the *CPUPlayer*

When launching the game, select an *ia_level...* prefixed file.

## Create a level

To create a level you need to make a new *CSV* file, and place it in `src/view/levels`.

The code used in the CSV is :

| Code | Item     |
| :------------- | :------------- |
| 0 | Empty ground       |
| 1 | Wall |
| 2 | BOX |
| 3 | Box on Goal |
| 4 | Goal |
| 5 | Mario |
| 6 | Mario on Goal |

You can find inspiration in the existing files in the directory.

To create a level :
 - for the Robot (`CPUPlayer`), give it a name beginning with "ia_level". **Note** : The resolution is complex, this is why you must create a reasonable sized grid.
 - for you human player : name it however you want
