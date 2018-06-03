(ns hangman.core
  (:require [hangman.game])
  (:gen-class))

(defn -main
  "starts the game"
  [& args]
  (hangman.game/init-game))
