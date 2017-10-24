/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.account;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("accounts")
public class AccountResource {

    @GET
    public Response getAllAccounts() {
        List<Account> accounts = Database.getAccounts();
        return Response.ok(accounts).build();
    }

    @POST
    @Path("init")
    public Response initAccounts() {
        Database.initDatabase();
        return Response.ok().build();
    }

    @GET
    @Path("{accountId}")
    public Response getAccount(@PathParam("accountId") String accountId) {
        Account account = Database.getAccounts(accountId);
        return account != null
                ? Response.ok(account).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("{accountId}/name")
    public Response getAccountName(@PathParam("accountId") String accountId) {
        Account account = Database.getAccounts(accountId);
        return account != null
                ? Response.ok().entity(account.getFirstName() + " " + account.getLastName()).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewAccount(Account account) {
        Database.addAccounts(account);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{accountId}")
    public Response deleteAccount(@PathParam("accountId") String accountId) {
        Database.deleteAccounts(accountId);
        return Response.noContent().build();
    }
}
