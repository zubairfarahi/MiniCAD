# MiniCAD
CAD [1] (Computer-Aided Design or Computer-Aided Design and Drawing) is the use of technology to create designs (generally 2D) and replaces the manual drawing process of a piece with an automated one. One of the most popular such programs is AutoCAD. Such programs improve the designer's design quality and productivity.

ok know the actual program what i have done in it , i am gone explane every file below this line:
Main.class
	This provides the entry point to the program, it accepts a file or directory. If a directory is passed in then all files in that directory will be used to create an image from the set instructions in the file.
CAD.class
	Provides reference to set and get the pixels for the output image as well as getters for the width and height. Uses singleton pattern.
Canvas.class
	A type of Shape which must be declared first in an instruction set. This contains the width and height of the output image as well as the background color
Image.class
	Wrapper for the actual image, hides unnecessary methods and ensures pixels being set fall within the image bounds
ShapeVisitor.class
	An interface to allow extensibility of the current program, e.g. printing to a physical printer, printing to screen etc.
ShapeDrawVisitor.class
	This contains all the logic for drawing
ShapeFactory.class
	Returns shapes based on the string array input. Returns shapes of the type below as well as Canvas. Uses Factory pattern.
		Circle
		Diamond
		Line
		Polygon
		Rectangle
		Square
		Triangle
Shape
	Interface for all shape types. Uses visitor pattern.
