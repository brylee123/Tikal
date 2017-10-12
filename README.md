# Tikal
University at Buffalo CSE 116 - Intro to Computer Science II Fall 2014 Final Project. Tikal Game.


Tikal is a game of exploration within the Central American jungles in search of lost temples and the treasures within. Players send their team of explorers into the jungle, exposing more and more of the terrain. Along the way, you find temples that require further uncovering and treasures. Players attempt to score points for occupying temples and holding onto treasure.

Game Rules:

Action Points
 All players alternate turns after each round. Each player starts off with a total of twelve (12) Action Points (APs) to use on their turn.
 Placing an explorer costs 2 AP.
 Placing a Terrain tile costs 6 APs. A player may choose to place more than one Terrain
tile per turn.
 Placing a Pyramid Piece costs 3 AP.
 Moving an explorer costs 1 AP per brick (path segment) they move along.

Constraints
 The first Terrain tile must be placed along an edge.
 Explorers can only be placed directly on the first tile.
 When moving an explorer across a path all segments must be traversed
 Subsequent tiles must be placed so they are reachable from an existing tile.
 A player can place at most two Pyramid Pieces per turn.
 A Pyramid Piece labeled N can only be place on a Pyramid Piece labeled N-1.
 A player can place a Pyramid Piece only if they have the largest number of explorers
on the Terrain tile.
 Once placed on the board Terrain tiles and Pyramid Pieces cannot be moved.


Game End
 The game ends at the end of the turn during which the last Terrain tile is placed.

Current Release
The current release of our game functions as follows:
A player may place a tile, place an explorer on a tile, and place a pyramid.
For now, pyramids are separate and infinite; they are incremented by the pyramid button. The game cannot be saved and volcanoes do not exist.
Scoring is done when the score button is activated which also ends the game.
