(ns hangman.game-test
  (:require [clojure.test :refer :all]
            [hangman.game :refer :all]))

(let [alphabet 
      (set "abcdefghijklmnopqrstuvwxyz")]
  
  (deftest hide-test
    (testing "simple"
      (is (= (hide "hello" alphabet)
             "-----")))
    (testing "with special chars"
      (is (= (hide "hello, world!" alphabet)
             "-----, -----!"))))
  
  (deftest reveal-test
    (testing "with special chars"
      (is (= (reveal "hello, world!" alphabet '#{\e \w})
             "-e---, w----!"))))
  
  (deftest guess-test
    (testing "wrong guesses"
      (is (= (wrong-guesses "hello" (set "tes"))
             '#{\s \t})))
    (testing "right guesses"
      (is (= (right-guesses "hello" (set "tes"))
             '#{\e}))))
    
  (deftest won?-test
    (testing "player has won"
      (is (won? "hello" alphabet (set "helo"))))
    (testing "player has not won"
      (is (not (won? "hello" alphabet (set "help")))))))

(run-tests)
