import matplotlib.pyplot as plt
import os

xpoints = []
ypoints = []

with open("data.txt", 'r') as file:
    line = file.readline()
    line = line.strip()
    line = line.split(',')
    for l in line:
        xpoints.append(float(l))
    line = file.readline()
    line = line.strip()
    line = line.split(',')
    for l in line:
        ypoints.append(float(l))


os.remove("data.txt")


plt.plot(xpoints, ypoints)
plt.xlabel("p")
plt.ylabel("Ce")
plt.title("Zavisnost broja gresaka u prenesenom tekstu \n od vjerovatnoce greske u kanalu")
plt.grid(True)

plt.show()