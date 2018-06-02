(ns hangman.game
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(defn hide [word alphabet]
  "Returns an obscured version of word, with all characters
   in alphabet replaced with \\-."
   (->> word
    (map #(if (contains? alphabet %) \- %))
    (str/join)))

(defn reveal [word alphabet letters]
  "Applies hide to word, but with all charactes in
   letters revealed."
  (hide word (set/difference alphabet letters)))

(defn wrong-letters [word letters]
  "Returns the set of wrongly guessed chars."
  (set/difference letters (set word)))

(defn right-letters [word letters]
  "Returns the set of correctly guessed chars."
  (set/intersection (set word) letters))

(defn won? [word alphabet letters]
  "Returns true if all the chars in the word have
   been guessed, otherwise returns false."
  (-> (set word)
    (set/intersection alphabet)
    (set/difference letters)
    (empty?)))

(comment
  (let [word "hello, world!"
        alphabet (set "abcdefghijklmnopqrstuvwxyz")]
    (hide word alphabet)) 
  (let [word "hello, world!"
        alphabet (set "abcdefghijklmnopqrstuvwxyz")] 
    (reveal word alphabet '#{\e \w}))
  (let [word "hello"
        letters (set "tes")]
    (wrong-letters word letters))
  (let [word "hello"
        letters (set "tes")]
    (right-letters word letters))
  (let [word "hello"
        letters (set "heloz")
        alphabet (set "abcdefghijklmnopqrstuvwxyz")]
    (won? word alphabet letters))
)