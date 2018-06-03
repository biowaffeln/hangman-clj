(ns hangman.game
  (:require [hangman.words]))

(defn print-game [word alphabet guesses]
  (println (str "\nyour word: \n"
                (reveal word alphabet guesses)
                "        "
                (count (wrong-guesses word guesses))
                "/10  "
                "wrong guesses: "
                (wrong-guesses word guesses))))

(defn sanitize-input [string]
  (-> string seq first))

(defn game-loop [word alphabet guesses]
  (if (won? word alphabet guesses)
    (println "\nyou won! yay")
    (if (lost? word guesses)
      (println (str "\nyou lost! the word was " word))
      (read-guess word alphabet guesses))))

(defn read-guess [word alphabet guesses]
  (let [guess (read-line)
        new-guesses (conj guesses (sanitize-input guess))]
      (do
       (print-game word alphabet new-guesses)
       (game-loop word alphabet new-guesses))))

(defn random-word []
  (let [words ["functional programming",
               "hello, world!",
               "clojure"]]
    (rand-nth words)))

(defn init-game []
  (let [word (random-word)
        alphabet (set "abcdefghijklmnopqrstuvwxyz")
        guesses '#{}]
    (do 
      (print-game word alphabet guesses)
      (game-loop word alphabet guesses))))
