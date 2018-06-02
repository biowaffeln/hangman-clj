(ns hangman.game-test
  (:require [clojure.test :refer :all]
            [hangman.game :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))


(comment
  (let [word "hello, world!"
        alphabet (set "abcdefghijklmnopqrstuvwxyz")]
    (hide word alphabet)) 
  (let [word "hello, world!"
        alphabet (set "abcdefghijklmnopqrstuvwxyz")] 
    (reveal word alphabet '#{\e \w}))
  (let [word "hello"
        guesses (set "tes")]
    (wrong-guesses word guesses))
  (let [word "hello"
        guesses (set "tes")]
    (right-guesses word guesses))
  (let [word "hello"
        guesses (set "heloz")
        alphabet (set "abcdefghijklmnopqrstuvwxyz")]
    (won? word alphabet guesses))
)