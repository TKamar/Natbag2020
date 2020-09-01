
import subprocess

from flask import Flask, request

app = Flask("natbag2020")



@app.route("/departures")
def dep():
    return subprocess.check_output(["java", "-classpath",
                                    "C:\\Users\\Tomer\\git\\Natbag2020\\AirPortData\\bin", "AirPortData.Main",
                                    request.args.get('outformat'), "departures",
                                    request.args.get('airline'), request.args.get('country'),
                                    request.args.get('day1'), request.args.get('month1'),
                                    request.args.get('year1'), request.args.get('day2'),
                                    request.args.get('month2'), request.args.get('year2')])

@app.route("/arrivals")
def arr():
    return subprocess.check_output(["java", "-classpath",
                                    "C:\\Users\\Tomer\\git\\Natbag2020\\AirPortData\\bin", "AirPortData.Main",
                                    request.args.get('outformat'), "arrivals",
                                    request.args.get('airline'), request.args.get('country'),
                                    request.args.get('day1'), request.args.get('month1'),
                                    request.args.get('year1'), request.args.get('day2'),
                                    request.args.get('month2'), request.args.get('year2')])

app.run(port=8000, host="0.0.0.0")