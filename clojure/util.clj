(defn fact
  "Return the factorial of integer n >= 0"
  [n]
  (apply *' (range 1 (inc n))))

(defn comb
  "Return the combination - ways of selecting k from n (k<=n)"
  [n k]
  (/ (fact n) (*' (fact k) (fact (- n k)))))

(def distr-specific
  (let [c5213 (comb 52 13)]
    (for [s (range 14)
          h (range 14)
          d (range 14)
          c (range 14)
          :when (= 13 (+ s h d c))]
      (let [cs (* (comb 13 s) (comb 13 h) (comb 13 d) (comb 13 c))
            pp (/ (* 100.0 cs) c5213)]
        [[s h d c] pp]))))

                                        ;=> ([[0 0 0 13] 1.5747695224491076E-10]
                                        ;    [[0 0 1 12] 2.661360492938992E-8]
                                        ;    [[0 0 2 11] 9.580897774580372E-7]
                                        ;    ...
                                        ;    [[3 4 3 3] 2.6340325788532972]
                                        ;    [[3 4 4 2] 1.7959313037636118]
                                        ;    ...
                                        ;    [[12 1 0 0] 2.661360492938992E-8]
                                        ;    [[13 0 0 0] 1.5747695224491076E-10])

(apply + (map second distr-specific))
                                        ;=> 100.00000000000003

(count distr-specific)
                                        ;=> 560
(map first distr-specific) ;=>
