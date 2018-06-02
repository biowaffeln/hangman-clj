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

(defn wrong-guesses [word guesses]
  "Returns the set of wrongly guessed chars."
  (set/difference guesses (set word)))

(defn right-guesses [word guesses]
  "Returns the set of correctly guessed chars."
  (set/intersection (set word) guesses))

(defn won? [word alphabet guesses]
  "Returns true if all the chars in the word have
   been guessed, otherwise returns false."
  (-> (set word)
    (set/intersection alphabet)
    (set/difference guesses)
    (empty?)))
