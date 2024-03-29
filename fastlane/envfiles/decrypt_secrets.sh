#!/bin/sh

# --batch to prevent interactive command
# --yes to assume "yes" for questions
gpg --quiet --batch --yes --decrypt --passphrase="$API_JSON_FILE_DECRYPTKEY" \
--output ./api-4712693179220384697-162836-33ea08672303.json api-4712693179220384697-162836-33ea08672303.json.gpg

gpg --quiet --batch --yes --decrypt --passphrase="$STORE_JKS_DECRYPTKEY" \
--output ./keystore.jks keystore.jks.gpg


gpg --quiet --batch --yes --decrypt --passphrase="$OBF_STORE_JKS_DECRYPTKEY" \
--output ./OpenBeautyFactsProd.key OpenBeautyFactsProd.key.gpg
