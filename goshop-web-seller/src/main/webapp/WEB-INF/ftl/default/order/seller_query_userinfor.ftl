<tr>
<td align="right" width="20%">姓名：</td>
<td align="left" width="80%">${(obj.addr.trueName)!}</td>
</tr>
<tr>
<td align="right" width="20%">电话：</td>
<td align="left" width="80%">${(obj.addr.telephone)!}</td>
</tr>
<tr>
<td align="right" width="20%">手机：</td>
<td align="left" width="80%">${(obj.addr.mobile)!}</td>
</tr>
<tr>
<td align="right" width="20%">邮编：</td>
<td align="left" width="80%">${(obj.addr.zip)!}</td>
</tr>
<tr>
<td align="right" valign="top" width="20%">地址：</td>
<td align="left" width="80%">${(obj.addr.area.parent.parent.areaName)!} ${(obj.addr.area.parent.areaName)!} ${(obj.addr.area.areaName)!} ${(obj.addr.area_info)!}</td>
</tr>
