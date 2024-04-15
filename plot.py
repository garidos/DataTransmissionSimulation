import matplotlib.pyplot as plt
import numpy as np
import os

xpoints = []
ypoints = []

with open("data.txt", 'r') as file:
    while 1:
        line = file.readline()
        if line == "":
            break
        line = line.strip()
        vals = line.split(',')
        xpoints.append(float(vals[0]))
        ypoints.append(float(vals[1]))


x = np.array(xpoints)
y = np.array(ypoints)

os.remove("./data.txt")

plt.plot(x, y, '.k')
plt.xlabel("p")
plt.ylabel("Pe")
plt.title("Zavisnost broja gresaka u prenesenom tekstu \n od vjerovatnoce greske u kanalu")
plt.grid(True)

plt.show()