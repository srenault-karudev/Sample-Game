JFLAGS = -g
JC = javac
J = java
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Fenetre.java \
		MenuJeu.java \
		Grille.java \
		EcranJeu.java \
		Case.java \
		ControleurMenu.java \
		Menu.java \
		Main.java

default: classes

classes: $(CLASSES:.java=.class)

test: classes
		$(J) Main

clean:
		$(RM) *.class
