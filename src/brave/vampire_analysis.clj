(ns brave.vampire-analysis
  (:require [clojure.string :as string]))


(def csv-file "resources/suspects.csv")


(def vamp-keys [:name :glitter-index])

(defn str->int
  [^String str]
  (Integer/parseInt str))

(def conversions {:name          identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(string/split % #",")
       (string/split-lines string)))


(defn mapify
  "Returns a seq of maps like {:name \"Edward Cullen\" :glitter-index 10"
  [rows]
  (map
    (fn [unmapped-row]
      (reduce (fn [row-map [vamp-key value]]
                (assoc row-map vamp-key (convert vamp-key value)))
              {}
              (map vector vamp-keys unmapped-row)))
    rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def minimum-3-glitter (partial glitter-filter 3))

(defn extract-names
  [records]
  (apply vector (map :name records)))

(-> csv-file
    (slurp)
    (parse)
    (mapify)
    (minimum-3-glitter)
    (extract-names))
