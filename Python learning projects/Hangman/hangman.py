import random

print("H A N G M A N")

while True:
    print('Type "play" to play the game, "exit" to quit:')
    choice = input()
    if choice == "exit":
        break
    elif choice == "play":
        word = random.choice(['python', 'java', 'kotlin', 'javascript'])
        printed_word = "-" * (len(word))
        life = 8
        used_letters = ""

        while life > 0:
            print()
            print(printed_word)
            if printed_word == word:
                print("You guessed the word!")
                print("You survived!")
                break
            guess = input('Input a letter: ')
            if len(guess) != 1:
                print("You should input a single letter")
            elif not guess.islower() or not guess.isalpha():
                print("Please enter a lowercase English letter")
            elif guess in used_letters:
                print("You've already guessed this letter")
            else:
                if guess in word:
                    new_printed_word = ""
                    for index in range(len(word)):
                        if guess == word[index]:
                            new_printed_word += guess
                        else:
                            new_printed_word += printed_word[index]

                    printed_word = new_printed_word
                    used_letters += guess
                else:
                    print("That letter doesn't appear in the word")
                    life -= 1
                    used_letters += guess
            if life == 0:
                print("You lost!")
                break
        break
