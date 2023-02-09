/*
 * Copyright 2013 Google Inc.
 * Copyright 2014 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.libdohj.params;

import org.bitcoinj.core.Utils;
import org.spongycastle.util.encoders.Hex;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the Dogecoin testnet, a separate public network that has
 * relaxed rules suitable for development and testing of applications and new
 * Dogecoin versions.
 */
public  class DogecoinTestNet3Params extends AbstractDogecoinParams {
    public static final int TESTNET_MAJORITY_WINDOW = 1000;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 750;
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 501;
    protected static final int DIFFICULTY_CHANGE_TARGET = 145000;

    public DogecoinTestNet3Params() {
        super(DIFFICULTY_CHANGE_TARGET);
        id = ID_TESTNET;

        packetMagic = 0xfcc1b7dc;

        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        port = 9888;
        addressHeader = 113;
        p2shHeader = 196;
        dumpedPrivateKeyHeader = 241;
        segwitAddressHrp = "trdc";
        genesisBlock.setTime(1486949366L);
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setNonce(211209);
        spendableCoinbaseDepth = 10;
        subsidyDecreaseBlockCount = 100000;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("00000a3c106109877cca71fb172796779090fb34d49147b2761a5ba8f451dfc3"));

        majorityEnforceBlockUpgrade = TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = TESTNET_MAJORITY_WINDOW;

        dnsSeeds = new String[] {
            "testseed.jrn.me.uk"
        };
        // Note this is the same as the BIP32 testnet, as BIP44 makes HD wallets
        // chain agnostic. Dogecoin mainnet has its own headers for legacy reasons.
        bip32HeaderP2PKHpub = 0x043587CF;
        bip32HeaderP2PKHpriv = 0x04358394;
    }

    private static DogecoinTestNet3Params instance;
    public static synchronized DogecoinTestNet3Params get() {
        if (instance == null) {
            instance = new DogecoinTestNet3Params();
        }
        return instance;
    }

    @Override
    public boolean allowMinDifficultyBlocks() {
        return true;
    }

    @Override
    public String getPaymentProtocolId() {
        // TODO: CHANGE ME
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }

    @Override
    public boolean isTestNet() {
        return true;
    }
}
