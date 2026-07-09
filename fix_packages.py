import os

replacements = {
    "package com.assabloy.livvi.streakdays.ui.create": "package com.assabloy.livvi.streakdays.feature.create.ui",
    "package com.assabloy.livvi.streakdays.ui.dashboard": "package com.assabloy.livvi.streakdays.feature.dashboard.ui",
    "package com.assabloy.livvi.streakdays.ui.details": "package com.assabloy.livvi.streakdays.feature.details.ui",
    "package com.assabloy.livvi.streakdays.ui.navigation": "package com.assabloy.livvi.streakdays.core.navigation",
    "package com.assabloy.livvi.streakdays.ui.theme": "package com.assabloy.livvi.streakdays.core.designsystem",
    "package com.assabloy.livvi.streakdays.ui.widget": "package com.assabloy.livvi.streakdays.core.widget"
}

for root, dirs, files in os.walk("app/src/main/java"):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r") as f:
                content = f.read()
            original = content
            for k, v in replacements.items():
                content = content.replace(k, v)
                
            if content != original:
                with open(path, "w") as f:
                    f.write(content)
