package ml.onnx;

import ai.onnxruntime.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

public class Inference {

    public static void main(String[] args) throws OrtException {
      float[][] rawInput = { { 1f, 1f, 1f }, { 1f, 1f, 1f }, {1f, 1f, 1f} };
      double[] outputRaw;

      String modelPath = "/home/danilo/IdeaProjects/MySpikes/spike_nn/models/test-model.onnx";
      OrtEnvironment environment = OrtEnvironment.getEnvironment();
      OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();
      OrtSession ortSession = environment.createSession(modelPath, sessionOptions);

      OnnxTensor inputTensor = OnnxTensor.createTensor(environment, rawInput);

      Map<String, OnnxTensor> input = new HashMap<String, OnnxTensor>() {{
         put("dense_input", inputTensor);
      }};

      try(OrtSession.Result result = ortSession.run(input)) {
          OnnxValue outputValue = result.get(0);
          OnnxTensor tensor = (OnnxTensor) outputValue;
          FloatBuffer buffer = tensor.getFloatBuffer();
          outputRaw = extractFeatures(buffer, 12);
      }

      ortSession.close();
      System.out.println("outputRaw length" + outputRaw.length);

    }

    private static double[] extractFeatures(FloatBuffer buffer, int flatSize) {
        double[] features = new double[flatSize];
        float[] floatArr = new float[flatSize];
        buffer.get(floatArr);
        for (int i = 0; i < floatArr.length; i++) {
            features[i] = floatArr[i];
        }
        return features;
    }

}
