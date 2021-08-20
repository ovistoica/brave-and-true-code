(ns brave.in-depth-functions)


;; Lazy seqs

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(comment (take 10 (even-numbers 15)))




