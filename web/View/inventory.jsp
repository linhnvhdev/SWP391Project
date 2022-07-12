<div id="inventory">
    <input type="hidden" name="health-potion-active" value="0">
    <div id="inventory-head">
        <h3>Inventory</h3>
    </div>
    <div id="inventory-list">
        <table id="inventory-table">
            <tr class="inventory-list-item inventory-table-header">
                <td class="inventory-list-item-icon">
                </td>
                <td class="inventory-list-item-name">
                    Name
                </td>
                <td class="inventory-list-item-number">
                    Amount
                </td>
                <td class="inventory-list-item-description">
                    Description
                </td>
                <td class="inventory-list-item-use">
                    Action
                </td>
            </tr>
        </table>
    </div>
    <div id="inventory-foot">
        <button id="closeInventory">Close</button>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/inventory.js?version=1"></script>
