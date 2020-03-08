(ns pi-digits.core
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

(defn pow [b e] (math/expt b e))
(defn abs [n] (math/abs n))

(defn arccot-while
  [x]
  (let [power (atom (quot (pow 10 319) x))
        total (atom @power)
        divisor (atom 1)]
    (while (>= (abs @power) @divisor)
      (do
        (swap! power #(quot (- %) (pow x 2)))
        (swap! divisor #(+ % 2))
        (swap! total #(+ % (quot @power @divisor)))))
    @total))

(defn arccot-recur
  [x]
  (loop [power (quot (pow 10 319) x)
         divisor  1
         tot 0]
    (let [total (+ tot (quot power divisor))]
      (if (>= (abs power) divisor)
        (recur (quot (- power) (pow x 2)) (+ divisor 2) total)
        total))))

;; Pick one
(def arccot arccot-recur)
;;(def arccot arccot-while)

(defn pi
  "Calculate Pi to 314 digits"
  []
  (let [r (str (* 4 (- (* 4 (arccot 5)) (arccot 239))))]
    (str (subs r 0 1) "." (subs r 1 315))))

(defn -main
  [& _]
  (println "314 digits of Pi" (time (pi))))
