SOURCE = Blackjack.java College.java Drugs.java Minigame.java Party.java \
		 PassiveStudy.java Piracy.java Player.java Roommate.java \
		 Studying.java

run: build
	java College

build: $(SOURCE)
	javac $(SOURCE)
