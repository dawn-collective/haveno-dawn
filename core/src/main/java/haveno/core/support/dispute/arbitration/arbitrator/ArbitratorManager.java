/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of Haveno.
 *
 * Haveno is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Haveno is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Haveno. If not, see <http://www.gnu.org/licenses/>.
 */

 package haveno.core.support.dispute.arbitration.arbitrator;

 import com.google.inject.Inject;
 import com.google.inject.Singleton;
 import haveno.common.config.Config;
 import haveno.common.crypto.KeyRing;
 import haveno.core.filter.FilterManager;
 import haveno.core.support.dispute.agent.DisputeAgentManager;
 import haveno.core.user.User;
 import haveno.network.p2p.storage.payload.ProtectedStorageEntry;
 import java.util.List;
 import lombok.extern.slf4j.Slf4j;
 
 @Slf4j
 @Singleton
 public class ArbitratorManager extends DisputeAgentManager<Arbitrator> {
 
     @Inject
     public ArbitratorManager(KeyRing keyRing,
                              ArbitratorService arbitratorService,
                              User user,
                              FilterManager filterManager) {
         super(keyRing, arbitratorService, user, filterManager);
     }
 
     @Override
     protected List<String> getPubKeyList() {
         switch (Config.baseCurrencyNetwork()) {
         case XMR_LOCAL:
             return List.of();
         case XMR_STAGENET:
             return List.of();
         case XMR_MAINNET:
             return List.of(
                     "0226b1130832b1c447d1cedfa0a0463c0a24748b0de5a01e5ae3cabd2fff25c6ae",
                     "039869c0b2b8e0da59c68b88264eee1de397e4f2aa75dcbc095a38a52836ac06e4",
                     "03916d0a711102b1b354e55be998512ca83f11671ef27b1148ec28554c84c5db83",
                     "02774b0ded6d2150ed8d9a5eb37923e62c39ceb39e56d39942e4d624d3d427c05f",
                     "03a458e49b6188ab996f8bf8853a2e7e429b8978234e88c79645f1f02411a2c0ff");
         default:
             throw new RuntimeException("Unhandled base currency network: " + Config.baseCurrencyNetwork());
         }
     }
 
     @Override
     protected boolean isExpectedInstance(ProtectedStorageEntry data) {
         return data.getProtectedStoragePayload() instanceof Arbitrator;
     }
 
     @Override
     protected void addAcceptedDisputeAgentToUser(Arbitrator disputeAgent) {
         user.addAcceptedArbitrator(disputeAgent);
     }
 
     @Override
     protected void removeAcceptedDisputeAgentFromUser(ProtectedStorageEntry data) {
         user.removeAcceptedArbitrator((Arbitrator) data.getProtectedStoragePayload());
     }
 
     @Override
     protected List<Arbitrator> getAcceptedDisputeAgentsFromUser() {
         return user.getAcceptedArbitrators();
     }
 
     @Override
     protected void clearAcceptedDisputeAgentsAtUser() {
         user.clearAcceptedArbitrators();
     }
 
     @Override
     protected Arbitrator getRegisteredDisputeAgentFromUser() {
         return user.getRegisteredArbitrator();
     }
 
     @Override
     protected void setRegisteredDisputeAgentAtUser(Arbitrator disputeAgent) {
         user.setRegisteredArbitrator(disputeAgent);
     }
 }
 