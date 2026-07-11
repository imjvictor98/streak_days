import os

replacements = {
    "package com.cvj.app.streakdays.ui.create": "package com.cvj.app.streakdays.feature.create.ui",
    "package com.cvj.app.streakdays.ui.dashboard": "package com.cvj.app.streakdays.feature.dashboard.ui",
    "package com.cvj.app.streakdays.ui.details": "package com.cvj.app.streakdays.feature.details.ui",
    "package com.cvj.app.streakdays.ui.navigation": "package com.cvj.app.streakdays.core.navigation",
    "package com.cvj.app.streakdays.ui.theme": "package com.cvj.app.streakdays.core.designsystem",
    "package com.cvj.app.streakdays.ui.widget": "package com.cvj.app.streakdays.core.widget"
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
