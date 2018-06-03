(ns hangman.game
  (:require [clojure.string :as str])
  (:require [clojure.set :as set]))

(defn hide
  "Returns an obscured version of word, with all characters
   in alphabet replaced with \\-."
  [word alphabet]
  (->> word
       (map #(if (contains? alphabet %) \- %))
       (str/join)))

(defn reveal
  "Applies hide to word, but with all charactes in
   letters revealed."
  [word alphabet letters]
  (hide word (set/difference alphabet letters)))

(defn wrong-guesses
  "Returns the set of wrongly guessed chars."
  [word guesses]
  (set/difference guesses (set word)))

(defn right-guesses
  "Returns the set of correctly guessed chars."
  [word guesses]
  (set/intersection (set word) guesses))

(defn won?
  "Returns true if all the chars in the word have
   been guessed, otherwise returns false."
  [word alphabet guesses]
  (-> (set word)
      (set/intersection alphabet)
      (set/difference guesses)
      (empty?)))

(defn lost? [word guesses]
  (> (count (wrong-guesses word guesses))
     10))

(comment
  (lost? "jazz" (set "etaoinshrdljc"))
)