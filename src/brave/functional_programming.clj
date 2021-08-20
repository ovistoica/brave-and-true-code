(ns brave.functional-programming)

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))


(comment (time (sum [1 2 3 4 5] 3)))

;; Function composition
(def character
  {:name       "Smooches McCutes"
   :attributes {:intelligence 10
                :strength     4
                :dexterity    5}})


;; comp takes a list of functions and applies them from last to first
;; The last one can take any number of arguments but the other ones
;; must take only one
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

