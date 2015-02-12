/** The qualification of a referee */
public enum RefQualification {
    NJB ("National"), IJB ("International");
    private String value;
    public static int MAXIMUM = 4;
    
    private RefQualification(String value) {
	this.value = value;
    }
    
    @Override
    public String toString() {
	return value;
    }
    
//    @Override
//    public String toString() {
//         switch (this) {
//           case NJB:
//                return  "National";
//           case IJB:
//                return "International";
//          }
//    return super.toString();
//   }

}