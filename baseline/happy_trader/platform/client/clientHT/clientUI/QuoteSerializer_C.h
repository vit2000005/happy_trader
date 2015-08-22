/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_fin_httrader_utils_win32_QuoteSerializer */

#ifndef _Included_com_fin_httrader_utils_win32_QuoteSerializer
#define _Included_com_fin_httrader_utils_win32_QuoteSerializer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    serializeRtDataToBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtRtData;[B)V
 */
JNIEXPORT void JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_serializeRtDataToBinary
  (JNIEnv *, jclass, jobject, jbyteArray);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    deserializeRtDataFromBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtRtData;[B)V
 */
JNIEXPORT void JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_deserializeRtDataFromBinary
  (JNIEnv *, jclass, jobject, jbyteArray);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    getHtRtDataSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_getHtRtDataSize
  (JNIEnv *, jclass);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    serializeLevel1ToBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtLevel1Data;[B)V
 */
JNIEXPORT void JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_serializeLevel1ToBinary
  (JNIEnv *, jclass, jobject, jbyteArray);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    deserializeLevel1FromBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtLevel1Data;[B)V
 */
JNIEXPORT void JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_deserializeLevel1FromBinary
  (JNIEnv *, jclass, jobject, jbyteArray);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    getLevel1DataSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_getLevel1DataSize
  (JNIEnv *, jclass);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    serializeLevel2ToBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtLevel2Data;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_serializeLevel2ToBinary
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_fin_httrader_utils_win32_QuoteSerializer
 * Method:    deserializeLevel2FromBinary
 * Signature: (Lcom/fin/httrader/utils/hlpstruct/HtLevel2Data;[B)V
 */
JNIEXPORT void JNICALL Java_com_fin_httrader_utils_win32_QuoteSerializer_deserializeLevel2FromBinary
  (JNIEnv *, jclass, jobject, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
