import java.util.HashMap;

class RnaTranscription {

    String transcribe(String dnaStrand) {
        HashMap<String, String> geneMap = new HashMap<String,String>();
        geneMap.put("G","C");
        geneMap.put("C","G");
        geneMap.put("T","A");
        geneMap.put("A","U");

        String response = "";
        if (dnaStrand.length() > 0) {
            //encode the string in an array
            for (int i = 0; i<dnaStrand.length(); i++) {
                String s = Character.toString(dnaStrand.charAt(i));
                response = response.concat(geneMap.get(s));
            }
        }
        return response;
    }

}
