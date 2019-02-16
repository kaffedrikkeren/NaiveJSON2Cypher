(ns naive-converter
  (:require [clojure.java.io :as io])
  (:require [clojure.data.json :as json])
  (:gen-class))

(def new-node-query "CREATE (n:%s {uid: \"%s\");\n\n")
(def new-link-query (str "MATCH (from:%s {uid: \"%s\"}), (to:%s {uid: \"%s\"})\n"
                         "CREATE (from)-[r:BELONGS_TO]->(to);\n\n"))

(defn get-file-contents [file-name]
  (io/reader file-name))

(defn generate-cypher [first-id second-id]
  (printf new-node-query (first-id :type) (first-id :uid))
  (printf new-node-query (second-id :type) (second-id :uid))
  (printf new-link-query
          (first-id :type)
          (first-id :uid)
          (second-id :type)
          (second-id :uid)))

(defn string-to-json [input-string]
  (json/read-str input-string :key-fn keyword))

(defn process-file [file-name]
  (let [file-contents (get-file-contents file-name)]
    (doseq [line (line-seq file-contents)]
      (let [id-link (string-to-json line)]
        (let [first-id (first id-link)
              second-id (second id-link)]
          (generate-cypher first-id second-id))))))

(defn -main [& args]
  (process-file (first args))
  (flush))

