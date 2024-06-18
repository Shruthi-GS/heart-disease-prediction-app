# from flask import Flask, request, jsonify
# import pickle
# import numpy as np
#
# model = pickle.load(open('model1.pkl','rb'))
#
# app = Flask(__name__)
#
# @app.route('/')
# def home():
#     return "Heart Disease Prediction App"
#
# @app.route('/predict',methods=['POST'])
#
# #'cp','thalach','slope','restecg','chol','trestbps','fbs','oldpeak'
# #[0,108,1,0,250,160,1,1.5] 0
# #[3,150,0,0,233,145,1,2.3] 1
#
# def predict():
#     cp = request.form.get('cp')
#     thalach = request.form.get('thalach')
#     slope = request.form.get('slope')
#     restecg = request.form.get('restecg')
#     chol = request.form.get('chol')
#     trestbps = request.form.get('trestbps')
#     fbs = request.form.get('fbs')
#     oldpeak = request.form.get('oldpeak')
#
#     #result = {'cp':cp,'thalach':thalach,'slope':slope,'restecg':restecg,'chol':chol,'trestbps':trestbps,'fbs':fbs,'oldpeak':oldpeak}
#
#     input_query = np.array([[cp,thalach,slope,restecg,chol,trestbps,fbs,oldpeak]])
#
#     result = model.predict(input_query)[0]
#
#     return jsonify({'hearth_disease': str(result)})
#
#
#
# if __name__=='__main__':
#     app.run(debug=True)




# from flask import Flask, request, jsonify
# import pickle
# import numpy as np
# import xgboost as xgb
#
# # Load the trained model
# model = pickle.load(open('heart_disease_model.pkl', 'rb'))
#
# app = Flask(__name__)
#
# @app.route('/')
# def home():
#     return "Heart Disease Prediction App"
#
# @app.route('/predict', methods=['POST'])
# def predict():
#     cp = float(request.form.get('cp'))
#     thalach = float(request.form.get('thalach'))
#     slope = float(request.form.get('slope'))
#     restecg = float(request.form.get('restecg'))
#     chol = float(request.form.get('chol'))
#     trestbps = float(request.form.get('trestbps'))
#     fbs = float(request.form.get('fbs'))
#     oldpeak = float(request.form.get('oldpeak'))
#
#     input_query = np.array([[cp, thalach, slope, restecg, chol, trestbps, fbs, oldpeak]])
#     dmatrix = xgb.DMatrix(input_query)
#     result = model.predict(dmatrix)
#     result_binary = 1 if result[0] > 0.5 else 0
#
#     return jsonify({'heart_disease': str(result_binary)})
#
# if __name__ == '__main__':
#     app.run(debug=True)



from flask import Flask, request, jsonify
import pickle
import numpy as np
import xgboost as xgb

# Load the trained model
model = pickle.load(open('heart_disease_model (2).pkl', 'rb'))

app = Flask(__name__)

@app.route('/')
def home():
    return "Heart Disease Prediction App"

@app.route('/predict', methods=['POST'])
def predict():
    try:
        # Extracting all required fields from the request and convert to float
        age = float(request.form.get('age'))
        sex = float(request.form.get('sex'))
        cp = float(request.form.get('cp'))
        trestbps = float(request.form.get('trestbps'))
        chol = float(request.form.get('chol'))
        fbs = float(request.form.get('fbs'))
        restecg = float(request.form.get('restecg'))
        thalach = float(request.form.get('thalach'))
        exang = float(request.form.get('exang'))
        oldpeak = float(request.form.get('oldpeak'))
        slope = float(request.form.get('slope'))
        ca = float(request.form.get('ca'))
        thal = float(request.form.get('thal'))

        # Debug print statements
        #print(f"age: {age}, sex: {sex}, cp: {cp}, trestbps: {trestbps}, chol: {chol}, fbs: {fbs}, restecg: {restecg}, thalach: {thalach}, exang: {exang}, oldpeak: {oldpeak}, slope: {slope}, ca: {ca}, thal: {thal}")

        # Preparing the input for the model
        input_query = np.array([[age, sex, cp, trestbps, chol, fbs, restecg, thalach, exang, oldpeak, slope, ca, thal]], dtype=float)
        dmatrix = xgb.DMatrix(input_query)

        # Making prediction
        result = model.predict(dmatrix)
        result_binary = 1 if result[0] > 0.5 else 0

        return jsonify({'heart_disease': str(result_binary)})

    except Exception as e:
        return jsonify({'error': str(e)}), 400

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=5000,debug=True)


